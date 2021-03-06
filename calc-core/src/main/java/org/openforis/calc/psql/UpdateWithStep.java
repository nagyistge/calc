package org.openforis.calc.psql;

import org.jooq.Table;
import org.jooq.Update;
import org.jooq.impl.TableAliasUtil;

/**
 * 
 * @author G. Miceli
 * @author M. Togna
 *
 */
public class UpdateWithStep extends ExecutablePsqlPart {

	UpdateWithStep(Psql psql, Table<?> cursor, Update<?> update, Object joinCondition) {
		super(psql); 
		append("with ");
		append(cursor);
		append(" as (");
		append(TableAliasUtil.getAliasedTable(cursor));
		append(") ");
		append(update);
		append(" from ");
		append(cursor);
		append(" where ");
		append(joinCondition);
	}
}
