using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
public class Scenechange : MonoBehaviour
{
    public string Scenename;
    // Update is called once per frame
    public void scenechange()
    {
        SceneManager.LoadScene(Scenename);
    }


}
