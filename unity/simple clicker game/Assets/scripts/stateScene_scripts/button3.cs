using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class button3 : MonoBehaviour
{
    private state status;
    public Text text;
    public void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>();
        text.text = status.Getattack().ToString();
    }
    public void Onclick()
    {
        if (status.getGold()>= status.Getattack() * 100)
        {
            status.AddAttack(1);
            text.text = status.Getattack().ToString();
            status.SubGold(status.Getattack() * 100);
        }


    }

}
