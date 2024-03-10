public class Course {
    private String coursename;
    private String directorName;
    private String requirement;
    private String date;
    private String id;
    private DataControl dataControl; // 添加对DataControl的引用

    public Course(String coursename, String directorName, String requirement, String date, String id, DataControl dataControl) {
        this.coursename = coursename;
        this.directorName = directorName;
        this.requirement = requirement;
        this.date = date;
        this.id = id;
        this.dataControl = dataControl; // 初始化DataControl引用
    }

    public void saveCourse(){
        // 使用dataControl来保存课程信息到数据库
        dataControl.open(this.directorName);
        dataControl.wirte(this.directorName, this.coursename, this.requirement, this.date, this.id, this.dataControl);
        dataControl.create(this.date); 
        datacontrol.close(this.directorName);
    }

}
