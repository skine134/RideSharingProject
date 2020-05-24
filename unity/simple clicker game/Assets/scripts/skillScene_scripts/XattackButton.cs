using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class XattackButton : MonoBehaviour
{
    public Xattack xattack;
   public void OnClick()
    {
        xattack.level++;
    }
}
