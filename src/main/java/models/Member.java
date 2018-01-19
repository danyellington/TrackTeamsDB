package models;


public class Member {
        private String memberName;
        private String stats;
        private int id;
        private int teamId;


    public Member (String memberName, String stats, int teamId) {
        this.memberName = memberName;
        this.stats = stats;
        this.teamId = teamId;
    }


    public String getMemberName() {
        return memberName;
    }

    public void setName(String memberName) {
        this.memberName = memberName;
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

    public void getTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (teamId != member.teamId) return false;
        if (memberName != null ? !memberName.equals(member.memberName) : member.memberName != null) return false;
        return stats != null ? stats.equals(member.stats) : member.stats == null;
    }

    @Override
    public int hashCode() {
        int result = memberName != null ? memberName.hashCode() : 0;
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + teamId;
        return result;
    }
}

