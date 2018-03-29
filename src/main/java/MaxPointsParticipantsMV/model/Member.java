package MaxPointsParticipantsMV.model;

public class Member {

    private Integer id;
    private String name;

	public Member(String name, Integer id){
		this.name= name;
		this.id=id;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
