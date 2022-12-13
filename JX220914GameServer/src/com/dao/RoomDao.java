package com.dao;

import model.Room;

import java.util.List;

public interface RoomDao  {
    boolean insertRoom(Room room);

    List<Room> selectAll();

    boolean joinRoom(Room room);

    boolean deleteRoom(Room room);
}
