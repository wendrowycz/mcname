package eu.oakroot.mcname

import com.intellij.ide.AppLifecycleListener
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class McStartup: ProjectActivity, AppLifecycleListener {
    override suspend fun execute(project: Project) {
        val actionManager = ActionManager.getInstance()
        val e = AnActionEvent(
            null,
            DataContext.EMPTY_CONTEXT,
            ActionPlaces.UNKNOWN,
            Presentation(),
            actionManager,
            0
        )

        val action = actionManager.getAction("eu.oakroot.mcname.McPath")
        action.update(e)
    }
}
