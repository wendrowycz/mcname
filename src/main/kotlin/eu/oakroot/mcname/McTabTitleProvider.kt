package eu.oakroot.mcname

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

class McTabTitleProvider : EditorTabTitleProvider {
    private var mcFullPath = false
    override fun getEditorTabTitle(project: Project, file: VirtualFile): String {
        val propertiesComponent = PropertiesComponent.getInstance(project)
        mcFullPath = propertiesComponent.getBoolean(McSettings.MC_PATH_FLAG_KEY)
        if (!mcFullPath) {
            return file.name
        }

        val filePath = file.path.removePrefix(project.basePath.toString())
        return filePath.removePrefix(File.separator)
    }
}
