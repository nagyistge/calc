package org.openforis.calc.chain.pre;

import java.util.List;

import org.openforis.calc.engine.Task;
import org.openforis.calc.engine.Workspace;
import org.openforis.calc.metadata.AoiHierarchy;
import org.openforis.calc.metadata.AoiHierarchyLevel;
import org.openforis.calc.persistence.postgis.PsqlBuilder;

/**
 * Copies category tables into the output schema.  Fails if output schema already exists.
 * 
 * @author A. Sanchez-Paus Diaz
 * @author G. Miceli
 */
public final class CreateAoiDimensionTablesTask extends Task {

	private static final String CALC_AOI_TABLE = "calc.aoi";
	private static final String DIMENSION_ID_COLUMN = "id";
	private static final String AOI_LEVEL_ID_COLUMN = "aoi_level_id";

	@Override
	protected void execute() throws Throwable {
		Workspace workspace = getWorkspace();
		List<AoiHierarchy> hierarchies = workspace.getAoiHierarchies();
		for (AoiHierarchy hierarchy : hierarchies) {
			List<AoiHierarchyLevel> levels = hierarchy.getLevels();
			for (AoiHierarchyLevel level : levels) {
				String tableName = PsqlBuilder.quote(level.getDimensionTable());
				Integer varId = level.getId();

				PsqlBuilder select = new PsqlBuilder()
					.select("*")
					.from(CALC_AOI_TABLE)
					.where(AOI_LEVEL_ID_COLUMN+"=?");
				
				createPsqlBuilder()
					.createTable(tableName)
					.as(select) 
					.execute(varId);
				
				createPsqlBuilder()
					.alterTable(tableName)
					.addPrimaryKey(DIMENSION_ID_COLUMN);
			}
		}
	}

}