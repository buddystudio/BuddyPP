/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.debug;

import java.util.EventListener;

/**
 *
 * @author Boniz
 */
public interface BDProgressStatusListener extends EventListener 
{
	void ProgressEventHandler(BDProgressStatusEvent event);
}
