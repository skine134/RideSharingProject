using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class selectPanelactive : MonoBehaviour
{
    public GameObject Sub_panel;
    public GameObject item_panel;
    public GameObject skill_panel;
    private GameObject buttons;
    public static bool isclick;
   // public GameObject float_panel;
    void Awake()
    {
        isclick = false;
        buttons = GameObject.Find("buttons");
    }
    // Update is called once per frame
    public void OnClick()
    {
        Debug.Log(Sub_panel.GetComponent<RectTransform>().rect.height);
        if (!Sub_panel.activeSelf)
        {
            
            item_panel.SetActive(false);
            skill_panel.SetActive(false);
            Sub_panel.SetActive(true); 
            if (!isclick)
            {
                buttons.transform.position += new Vector3(0, 50);
            }
            isclick = true;
        }
        else
        {
            isclick = false;
            Sub_panel.SetActive(false); 
                buttons.transform.position -= new Vector3(0, 50);
        }
    }

}
