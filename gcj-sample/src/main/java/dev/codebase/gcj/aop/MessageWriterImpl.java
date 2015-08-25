package dev.codebase.gcj.aop;

public class MessageWriterImpl implements MessageWriter {

    @Override
    public void writeMessage() {
        System.out.println("World");
    }

}
