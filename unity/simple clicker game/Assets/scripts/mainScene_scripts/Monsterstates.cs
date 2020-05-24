using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Monsterstates : MonoBehaviour
{
    private int Hp = 100;
    private const int MaxHp = 100;
    private const int MinHp = 0;
    public GameObject hudDamageText;
    public Transform hudPos;
    public void setHp(int Hp)
    {
        this.Hp = Hp;
    }
    public int getHp()
    {
        return Hp;
    }
    public void DamagedHp(int damage)
    {
        if (Hp>=MinHp)
        {
            this.Hp = Hp - damage;
        }
        GameObject hudText = Instantiate(hudDamageText);
        hudText.transform.position = hudPos.position;
        hudText.GetComponent<DamageText>().damage = damage;
        Debug.Log(damage);

    }
    public void AutoBattleHeal(int ABH)
    {
        if (Hp <= MaxHp)
        {
            if (Hp + ABH < 100)
            {
                this.Hp = Hp + ABH;
            }
            else
                this.Hp = MaxHp;

        }
    }
   


}