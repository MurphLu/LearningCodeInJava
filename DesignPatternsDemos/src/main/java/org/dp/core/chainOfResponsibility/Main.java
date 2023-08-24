package org.dp.core.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        FilterChain filterChain2 = new FilterChain();
        filterChain2
                .addFilter(new HTMLFilter())
                .addFilter(new DatabaseFilter());

        filterChain
                .addFilter(new HTMLFilter())
                .addFilter(new DatabaseFilter())
                .addFilter(filterChain2);

        Msg msg = new Msg();
        msg.msg = "a";
        filterChain.doFilter(msg);
        System.out.println(msg.msg);
    }
}

class Msg{
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

abstract class Filter{
    abstract boolean doFilter(Msg msg);
}

class HTMLFilter extends Filter{
    public boolean doFilter(Msg msg) {
        System.out.println("remove html sensitive chars");
        return true;
    }
}

class DatabaseFilter extends Filter{
    public boolean doFilter(Msg msg) {
        System.out.println("remove Database sensitive chars");
        return false;
    }
}

class FilterChain extends Filter{
    List<Filter> filterList = new ArrayList<>();

    public FilterChain addFilter(Filter f) {
        filterList.add(f);
        return this;
    }

    public boolean doFilter(Msg m) {
        for (Filter filter: filterList) {
            if(!filter.doFilter(m)) return false;
        }
        return true;
    }
}


// 使用下面的实现可以实现 servlet 处理 req, resp 的过程，req 1-2-3, resp 3-2-1
abstract class ReqFilter {
    abstract boolean doFilter(Request req, Response resp, ServletFilterChain chain);
}

class AuthFilter extends ReqFilter {
    @Override
    boolean doFilter(Request req, Response resp, ServletFilterChain chain) {
        System.out.println("handle req");
        chain.doFilter(req, resp, chain);
        System.out.println("handle resp");
        return true;
    }
}

class ServletFilterChain extends ReqFilter {
    int index = 0;

    List<ServletFilterChain> filterList = new ArrayList<>();

    public ServletFilterChain addFilter(ServletFilterChain f) {
        filterList.add(f);
        return this;
    }

    @Override
    boolean doFilter(Request req, Response resp, ServletFilterChain chain){
        if(index == filterList.size()) return false;
        ReqFilter f = filterList.get(index);
        index ++;

        return f.doFilter(req, resp, chain);
    }
}
