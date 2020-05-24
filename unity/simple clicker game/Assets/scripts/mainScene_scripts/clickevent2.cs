using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class clickevent2 : MonoBehaviour
{
    private state status;
    public Animator char_anim;
    public Animator monster_anim;
    public Monsterstates monster_status;
    private void Awake()
    {
        status = GameObject.Find("Status").GetComponent<state>();
    }
    public void OnMouseDown()
    {
        
        status.AddGold(status.getGoldPer());
        char_anim.SetTrigger("OnClick");
        monster_anim.SetTrigger("Attacked");
        monster_status.DamagedHp(10);
    }
    
}
