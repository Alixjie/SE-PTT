public class Course {
    private String coursename;
    private String directorName;
    private String requirement;
    private String date;
    private String id;
    private DataControl dataControl; 

    public Course(String directorName, String date, String id, DataControl dataControl) {
        this.directorName = directorName;
        this.id = id;
        this.dataControl = dataControl;
    }

    public void saveCourse() {
        try {
            dataControl.open(); 
            dataControl.write(this.coursename, this.directorName, this.requirement, this.date, this.id);
            dataControl.close(); 
        } catch (Exception e) {
            System.out.println("Error occurred while saving the course: " + e.getMessage());
        } finally {
            try {
                dataControl.close();
            } catch (Exception e) {
                System.out.println("Error occurred while closing the data control: " + e.getMessage());
            }
        }
    }

}
