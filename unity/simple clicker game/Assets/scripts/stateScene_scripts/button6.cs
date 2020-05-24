using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class button6 : MonoBehaviour
{
    private state status;
    public Text text;
    private void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>();
        text.text = status.GetCriticalDamege().ToString();
    }
    public void OnClick()
    {
        status.AddCriticalDamege(1);
        text.text = status.GetCriticalDamege().ToString();
    }
}
