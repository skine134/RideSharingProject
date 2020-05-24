using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class saleItem : MonoBehaviour
{
    private state status;
    public Fadeinout fadeinout;
    public int price;
    public void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>();
    }
    public void OnClick()
    {
        if (status.getGold() >= price)
        {
            StartCoroutine(fadeinout.fadein("구매하였습니다."));
            status.setGold(status.getGold() - price);
        }
        else
        {
            StartCoroutine(fadeinout.fadein("골드가 부족합니다."));

        }
    }
}
