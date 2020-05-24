using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Attack2xButton : MonoBehaviour
{
    public Attack2x attack2X;

    public void OnClick()
    {
        attack2X.level++;
    }
}
