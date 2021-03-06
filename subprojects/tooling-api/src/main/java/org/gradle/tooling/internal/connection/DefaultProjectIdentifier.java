/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.internal.connection;

import org.gradle.tooling.model.BuildIdentifier;
import org.gradle.tooling.model.ProjectIdentifier;

import java.io.File;
import java.io.Serializable;

public class DefaultProjectIdentifier implements ProjectIdentifier, Serializable {
    private final BuildIdentifier build;
    private final String projectPath;

    public DefaultProjectIdentifier(BuildIdentifier build, String projectPath) {
        this.build = build;
        this.projectPath = projectPath;
    }

    public DefaultProjectIdentifier(File rootDir, String projectPath) {
        this(new DefaultBuildIdentifier(rootDir), projectPath);
    }

    public BuildIdentifier getBuildIdentifier() {
        return build;
    }

    @Override
    public String toString() {
        return String.format("project=%s, %s", projectPath, build);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultProjectIdentifier that = (DefaultProjectIdentifier) o;

        if (!build.equals(that.build)) {
            return false;
        }
        return projectPath.equals(that.projectPath);

    }

    @Override
    public int hashCode() {
        int result = build.hashCode();
        result = 31 * result + projectPath.hashCode();
        return result;
    }
}
