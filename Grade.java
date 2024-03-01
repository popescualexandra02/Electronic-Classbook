public class Grade implements Comparable, Cloneable {
    private Double partialScore, examScore;
    private Student student;
    private String course; // Numele cursului
    public Grade(String course, Student student) {
        partialScore = 0.0;
        examScore = 0.0;
        this.course = course;
        this.student = student;
    }

    public Grade(String course, Student student,Double partialScore, Double examScore ) {
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.course = course;
        this.student = student;
    }


    public void setPartialScore(Double partialScore){
        this.partialScore = partialScore;
    }

    public Double getPartialScore() {
        return partialScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setCourse(String course){
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public Double getTotal() {
        return getExamScore() + getPartialScore();
    }



    @Override
    public int compareTo(Object o) {
        Grade obj = (Grade) o;
        if(this.getTotal() == obj.getTotal())
            return 0;
        if(this.getTotal() > obj.getTotal())
            return 1;
        return -1;
    }

    public Object clone() {
// TODO
        return null;
    }

    @Override
    public String toString() {
        return "partialScore=" + partialScore +
                ", examScore=" + examScore
                ;
    }
}
