package eu.oakroot.mcname

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class McStartup : ProjectActivity {
    override suspend fun execute(project: Project) {
        ApplicationManager.getApplication().invokeLater ({
            // Get the ActionManager instance
            val actionManager = ActionManager.getInstance()
            // Get the action by its ID
            val action = actionManager.getAction("eu.oakroot.mcname.McPath")

            if (action != null) {
                // Execute the action in a way that lets the platform handle the update call
                actionManager.tryToExecute(action, null, null, ActionPlaces.UNKNOWN, true)
            }
        }, ModalityState.any())
    }
}