package ar.edu.unq.persistencia1.search.searching;


public class ConcreteCriteria extends SearchCriteria {

	private String field;

	private String value;

	public ConcreteCriteria(String field, String value) {
		setField(field);
		setValue(value);
	}

	public ConcreteCriteria() {
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
