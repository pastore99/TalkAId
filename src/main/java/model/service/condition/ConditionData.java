package model.service.condition;

import model.DAO.DAOCondition;
import model.entity.Condition;

public class ConditionData implements ConditionDataInterface{

    DAOCondition daoCondition=new DAOCondition();
    public Condition getConditionByID(int id){return daoCondition.getConditionByID(id);}
    public Condition getAllCondition(){return daoCondition.getAllCondition();}
}
