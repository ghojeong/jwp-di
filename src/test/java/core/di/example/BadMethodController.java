package core.di.example;

import core.annotation.Inject;

public class BadMethodController {

    private MyQnaService qnaService;

    @Inject
    public void setQnaService(MyQnaService qnaService, int number) {
        this.qnaService = qnaService;
    }
}
