package com.company.thejava.optional;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;

    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Optional 은 return 타입으로 쓰는 것만이 권장 사항이다!
     * @return
     */
    public Optional<Progress> getProgress() {
        /**
         * 1. Checked Exception 을 사용하면 에러 처리를 강제 하는 문제가 생긴다.
         * 2. 에러가 발생이 되면  Stack Trace를 찍는다! 어떤 callStack 을 거쳐서 에러가 발생하게 됐는지에 대한 정보를 담기 위해 리소스를 쓰게 된다.
         * => 우리가 진짜로 필요한 경우에만 Exception을 써야지 로직을 처리하기 위해서 Exception 을 쓰는 건 좋은 것이 아니다!

        if (this.progress == null) {
            throw new IllegalStateException();
        }
         */
        return Optional.ofNullable(progress);
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
