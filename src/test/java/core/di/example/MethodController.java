package core.di.example;

import core.annotation.Inject;

public class MethodController {
    private MyQnaService qnaService;

    @Inject
    public void setQnaService(MyQnaService qnaService) {
        this.qnaService = qnaService;
    }
}
