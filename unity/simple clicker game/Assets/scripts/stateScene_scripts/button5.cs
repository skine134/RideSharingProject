using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class button5 : MonoBehaviour
{
    private state status;
    public Text text;
    private void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>();
        text.text = status.GetCritical().ToString();
    }
    public void OnClick()
    {
        status.AddCritical(1);
        text.text = status.GetCritical().ToString();
    }
}
