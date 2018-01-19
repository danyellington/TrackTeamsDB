package models;


public class Member {
        private String name;
        private String stats;
        private int id;


    public Member (String name, String stats) {
        this.name = name;
        this.stats = stats;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (name != null ? !name.equals(member.name) : member.name != null) return false;
        return stats != null ? stats.equals(member.stats) : member.stats == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        return result;
    }
}

