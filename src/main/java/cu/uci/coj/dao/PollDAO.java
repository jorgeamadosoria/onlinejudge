package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.model.entities.Poll;

public abstract interface PollDAO
  extends BaseDAO
{
  public abstract void add(Poll paramPoll);

  public abstract void update(Poll paramPoll);

  public abstract void delete(int paramInt);
  
  public abstract void toggleEnable(int paramInt);
  
  public abstract void vote(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract Poll get(int paramInt);
  
  public abstract List<Poll> list();
  
  public abstract List<Poll> listEnabled(Integer paramInteger);
  
  public abstract boolean voted(Integer paramInteger1, Integer paramInteger2);
}
