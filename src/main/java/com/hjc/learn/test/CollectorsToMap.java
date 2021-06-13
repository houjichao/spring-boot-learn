package com.hjc.learn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class Answer {
    private int id;

    private Boolean answer;

    Answer() {
    }

    Answer(int id, Boolean answer) {
        this.id = id;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}

/**
 * @author houjichao
 */
public class CollectorsToMap {
    public static void main(String[] args) {
        List<Answer> answerList = new ArrayList<>();

        answerList.add(new Answer(1, true));
        answerList.add(new Answer(2, true));
        answerList.add(new Answer(3, null));

        //Map<Integer, Boolean> answerMap = answerList.stream().collect(Collectors.toMap(Answer::getId, Answer::getAnswer));
        /*
        运行之后的异常堆栈:
        Exception in thread "main" java.lang.NullPointerException
	at java.util.HashMap.merge(HashMap.java:1225)
	at java.util.stream.Collectors.lambda$toMap$58(Collectors.java:1320)
	at java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
	at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1382)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
	at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
	at com.hjc.learn.test.CollectorsToMap.main(CollectorsToMap.java:53)
	    原因：因为在Collectors.toMap中调用了map::merge方法，而map::merge对value做了空校验Objects::requireNonNull
         */
        Map< Integer,Boolean> answerMap = new HashMap<>();
        answerList.forEach(answer -> {
           answerMap.put(answer.getId(),answer.getAnswer());
        });
    }
}