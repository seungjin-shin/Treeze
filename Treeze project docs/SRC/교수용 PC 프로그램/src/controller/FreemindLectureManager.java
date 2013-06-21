package freemind.controller;

public class FreemindLectureManager {
	 
    private static LectureInfo instance;
    // 또는 private static Singleton instance = new Singleton();으로 미리 생성
 
    // 생성자 접근제한 키워드가 public이 아닌 private이다.
    // 따라서 클래스 내부에서만 사용가능한 생성자가 된다.
    private FreemindLectureManager() {  }
 
    // public : 어디서든 접근이 가능하다.
    // static : Singleton 인스턴스가 존재하지 않아도 사용가능한 메소드
    // 인스턴스를 받는 getter메소드
    // 반환은 Singleton으로 한다.
    public static LectureInfo getInstance() {
 
        // 인스턴스가 null이면 생성되지 않았다는 의미
        // 단 한번만 생성하는 싱글턴이라면
        // 위에서 인스턴스를 미리 생성해두고 if는 생략한다.
        if(instance == null) {
 
            // 위 if문이 true면(인스턴스가 없다면) 생성한다. 메모리 할당.
            instance = new LectureInfo();
 
        } // end if
 
        // false라면 이미 생성됐다는 의미.
        // 이미 있던, 새로 생성했던 싱글턴 인스턴스를 반환해준다.
        return instance;
 
    } // end getInstance()
} // end class
