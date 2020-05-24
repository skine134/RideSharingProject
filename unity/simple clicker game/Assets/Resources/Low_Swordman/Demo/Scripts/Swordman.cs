using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ClickBotton : MonoBehaviour
{
    public Animator anim;
    public void OnMouseDown()
    {
        anim.SetTrigger("OnClick");
    }
}