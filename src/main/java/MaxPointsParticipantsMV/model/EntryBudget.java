package MaxPointsParticipantsMV.model;

public class EntryBudget {

	private Integer value;
	private String typeEntry;
	private Integer idMember;

	public EntryBudget(String typeEntryBudget, Integer value, Integer idMember){
		this.typeEntry = typeEntryBudget;
		this.value = value;
		this.idMember = idMember;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getTypeEntry() {
		return typeEntry;
	}

	public void setTypeEntry(String typeEntry) {
		this.typeEntry = typeEntry;
	}

	public Integer getIdMember() {
		return idMember;
	}

	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}

	@Override
	public String toString() {
		return "EntryBudget{" +
				"value=" + value +
				", typeEntry=" + typeEntry +
				", idMember=" + idMember +
				'}';
	}
}
