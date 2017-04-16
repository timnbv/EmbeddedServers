package com.epam.jmp;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tim Ryzhov
 */
public class MemoryConsumption {

    public static Map<String, String> getMemoryInUse() {
        HashMap<String, String> result = new HashMap<String, String>();
        ArrayList<String> array = new ArrayList<String>();
        long heapSize = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        result.put("Heap Size", Long.toString(toKb(heapSize)) + "K");
        result.put("Heap Memory Usage", Long.toString(toKb(heapSize - freeMemory)) + "K");
        result.put("Thread Dump", crunchifyGenerateThreadDump());
        return result;
    }

    static Long toKb(long bytes) {
        return bytes / 1024;
    }

    public static void main(String[] args) {
        Map<String, String> memoryInUse = getMemoryInUse();
        System.out.println("Heap Size: " + memoryInUse.get("Heap Size") + " bytes");
        System.out.println("Heap Memory Usage: " + memoryInUse.get("Heap Memory Usage") + " bytes");
        System.out.println("Thread Dump: \n" + memoryInUse.get("Thread Dump"));
    }

    private static String crunchifyGenerateThreadDump() {
        final StringBuilder dump = new StringBuilder();
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        final ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), 100);
        for (ThreadInfo threadInfo : threadInfos) {
            dump.append('"');
            dump.append(threadInfo.getThreadName());
            dump.append("\" ");
            final Thread.State state = threadInfo.getThreadState();
            dump.append("\n   java.lang.Thread.State: ");
            dump.append(state);
            final StackTraceElement[] stackTraceElements = threadInfo.getStackTrace();
            for (final StackTraceElement stackTraceElement : stackTraceElements) {
                dump.append("\n        at ");
                dump.append(stackTraceElement);
            }
            dump.append("\n\n");
        }
        return dump.toString();
    }
}
