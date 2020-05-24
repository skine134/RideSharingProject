using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class category : MonoBehaviour
{
    public GameObject dhtpghksWkd;
    public GameObject ad;
   

    public void OnClick()
    {
        dhtpghksWkd.SetActive(true);
        if (dhtpghksWkd.active)
        {
            ad.SetActive(false);
            
        }
        
    }
    

    
}
