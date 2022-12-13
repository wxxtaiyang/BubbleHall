package com.dao;

import model.BattleProcess;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public interface BattleProcessDao  {

    boolean insertBattleProcess(Room room,String state);

    List<BattleProcess> queryBattleProcessByUser(String userid);
}
