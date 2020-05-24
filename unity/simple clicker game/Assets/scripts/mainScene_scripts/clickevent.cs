using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class clickevent : MonoBehaviour
{
    private state status;
    private Monsterstate monster;
    public Animator char_anim;
    public Animator monster_anim;
    private void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>();
        monster = GameObject.Find("monster_status").GetComponent<Monsterstate>();
    }
    public void OnMouseDown()
    {
        char_anim.SetTrigger("OnClick");
        monster_anim.SetTrigger("Attacked");
        monster.DamagedHp(status.Getattack());
        
    }
}
