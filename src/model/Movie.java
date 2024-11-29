package model;

// 영화 하나에 대한 데이터 정의
public class Movie {
    private Integer id;
    private String title;
    private String genre;
    private String age;

    private Integer runningTime;
    private String img;

    // 생성자
    public Movie(Integer id, String title, String genre, String age, Integer runningTime, String img) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.age = age;
        this.runningTime = runningTime;
        this.img = img;
    }

    // Getter
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public String getAge() {
        return age;
    }
    public Integer getRunningTime() {
        return runningTime;
    }
    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", age='" + age + '\'' +
                ", runningTime=" + runningTime +
                ", img='" + img + '\'' +
                '}';
    }

}
