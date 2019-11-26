package com.stackroute.contentservice.domain;


import java.util.Comparator;

public class SortByViews implements Comparator<Post> {
    public int compare(Post a, Post b)
    {
        return a.getWatchedBy().size() - b.getWatchedBy().size();
    }

}
