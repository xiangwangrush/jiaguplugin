package com.plugin.jiaguplugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.builder.model.SigningConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

class JiaGuPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        Info jiaguInfo = project.extensions.create("jiagu",Info)
        //gradle 解析完gradle 配置后的回调
        project.afterEvaluate {
            AppExtension androidExt = project.extensions.getByType(AppExtension.class)
            androidExt.applicationVariants.all {
                ApplicationVariant variant->
                    //对应渠道的签名信息
                    SigningConfig signingConfig = variant.signingConfig
                    variant.outputs.all {
                        BaseVariantOutput output->
                            //输出的apk文件
                            File apkOutput = output.outputFile
                            //创建加固任务
                            JiaguTask jgTask = project.tasks.create("jiagu${variant.baseName.capitalize()}",JiaguTask)
                            jgTask.jiaguInfo = jiaguInfo
                            jgTask.signingConfig = signingConfig
                            jgTask.apkOutput = apkOutput
                    }
            }
        }
    }
}