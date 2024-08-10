package eu.oakroot.mcname

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project

open class McPath : AnAction() {
    private var mcFullPath = false

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return
        val propertiesComponent = PropertiesComponent.getInstance(project)
        mcFullPath = !propertiesComponent.getBoolean(McSettings.MC_PATH_FLAG_KEY)
        propertiesComponent.setValue(McSettings.MC_PATH_FLAG_KEY, mcFullPath)

        val editorManager = FileEditorManager.getInstance(project)
        for (file in editorManager.openFiles) {
            editorManager.updateFilePresentation(file)
        }
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        val project: Project = e.project ?: return
        val propertiesComponent = PropertiesComponent.getInstance(project)
        mcFullPath = propertiesComponent.getBoolean(McSettings.MC_PATH_FLAG_KEY)
        e.presentation.isEnabledAndVisible = true
        e.presentation.text = if (mcFullPath) "Hide Full Path" else "Show Full Path"
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}
