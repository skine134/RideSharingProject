using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class button4 : MonoBehaviour
{
    private state status;
    public Text text;
    private void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>(); 
        text.text=status.GetAddDamege().ToString();
    }
    public void Onclick()
    {
        status.AddAddDamege(1);
        text.text=status.GetAddDamege().ToString();
    }
    
}
