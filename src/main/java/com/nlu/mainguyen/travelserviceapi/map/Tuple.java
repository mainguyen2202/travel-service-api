package com.nlu.mainguyen.travelserviceapi.map;

// Tạo đối tượng trung gian để return nhiều tham số
public class Tuple<T1, T2> {
    public final T1 Item1;
    public final T2 Item2;

    public Tuple(T1 item1, T2 item2) {
        Item1 = item1;
        Item2 = item2;
    }
}