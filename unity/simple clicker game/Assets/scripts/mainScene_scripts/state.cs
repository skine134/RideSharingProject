using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class state : MonoBehaviour
{
    private int my_gold;
    private int my_goldper;
    private int Attack;
    private int AddDamege;
    private float Critical;
    private float CriticalDamege;
    public static state instance = null;
    public static List<Item> items = new List<Item>();
    void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
        else
        {
            Destroy(gameObject);
        }
        DontDestroyOnLoad(gameObject);
        my_gold = 0;
        my_goldper = 10;
        Attack = 1;
        AddDamege = 1;
        Critical = 0;
        CriticalDamege = 0;

        items.Add(new Item(Item.Tag.material, "stone", 1, "100 Alchemy item icons-free\\092_stone"));
        items.Add(new Item(Item.Tag.equitment, "woodstick", 1, "100 Alchemy item icons-free\\004_tree"));
        items.Add(new Item(Item.Tag.potion, "stemina potion", 5, "100 Alchemy item icons-free\\066_power potion"));
        items.Add(new Item(Item.Tag.potion, "stemina potion", 5, "100 Alchemy item icons-free\\066_power potion"));
    }
    private void Start()
    {
        DontDestroyOnLoad(gameObject);
    }
    //Gold fungtion
    public void setGold(int gold)
    {
        my_gold = gold;
        PlayerPrefs.SetInt("Gold",my_gold);
    }
    public int getGold()
    {
        return my_gold;
    }
    public void AddGold(int gold)
    {
        my_gold += gold;
        setGold(my_gold);
    }
    public void SubGold(int gold)
    {
        my_gold -= gold;
        setGold(my_gold);
    }


    public void setAttack(int attact)
    {
        this.Attack = attact;
        PlayerPrefs.SetInt("Attact", this.Attack);
    }
    public int Getattack()
    {
        return this.Attack;
    }
    public void AddAttack(int attact)
    {
        this.Attack += attact;
        setAttack(this.Attack);
    }


    public void setAddDamege(int adddamege)
    {
        this.AddDamege = adddamege;
        PlayerPrefs.SetInt("AddDamege", this.AddDamege);
    }
    public int GetAddDamege()
    {
        return this.AddDamege;
    }
    public void AddAddDamege(int adddamege)
    {
        this.AddDamege += adddamege;
        setAddDamege(this.AddDamege); 
            }

    public void setCritical(float Critical)
    {
        this.Critical = Critical;
        PlayerPrefs.SetFloat("Critical", this.Critical);
    }
    public float GetCritical()
    {
        return this.Critical;
    }
    public void AddCritical(int Critical)
    {
        this.Critical += Critical;
        setCritical((int)this.Critical);
    }



    public void setCriticalDamege(float critical_Damege)
    {
        this.CriticalDamege = critical_Damege;
        PlayerPrefs.SetFloat("CriticalDamege", (int)this.CriticalDamege);
    }
    public float GetCriticalDamege()
    {
        return this.CriticalDamege;
    }
    public void AddCriticalDamege(float CriticalDamege)
    {
        this.CriticalDamege += CriticalDamege;
        setCriticalDamege(this.CriticalDamege);
    }


    //GoldPer fungtion
    public void setGoldPer(int goldPer)
    {
        my_goldper = goldPer;
        PlayerPrefs.SetInt("GoldPer", my_goldper);
    }
    public int getGoldPer()
    {
        return my_goldper;
    }
    public void AddGoldPer(int goldPer)
    {
        my_goldper += goldPer;
        setGoldPer(my_goldper);
    }
    public void SubGoldPer(int goldPer)
    {
        my_goldper -= goldPer;
        setGoldPer(my_goldper);
    }
    

}
