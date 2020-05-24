using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GoldTextEvent : MonoBehaviour
{
    private state status;
    private Text text;
    // Start is called before the first frame update
    void Start()
    {
        status = GameObject.Find("status").GetComponent<state>();
        text =GetComponent<Text>();
        text.text = status.getGold().ToString()+"G";
    }

    // Update is called once per frame
    void Update()
    {
        text.text = status.getGold().ToString() + "G";
        text.color = Color.yellow;
    }
}
