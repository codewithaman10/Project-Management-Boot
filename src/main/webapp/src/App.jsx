import NewProject from "./components/NewProject";
import ProjectSidebar from "./components/ProjectSidebar";
import DefaultScreen from "./components/DefaultScreen";
import ProjectDetails from "./components/ProjectDetails";
import { useReducer } from "react";
import { ProjectContext, ProjectDispatchContext, Reducer, initialState } from "./context/ProjectContext";

function App() {
  /**
     * In Case of useState we'll only have to pass the initial state and the useState will return 2 thing:
     * 1. Variable which will hold the current value of state variable per render
     * 2. A function to update the state variable
     * 
     * In case of useReducer we've to pass a Reducer function as well as the initial state and useReducer will return following 2 things:
     * 1. Variable which will hold the current value of state variable per render
     * 2. A dispatch function which will dispatch actions to Reducer in reponse of user doing something
     */
  const [projectsData, dispatch] = useReducer(Reducer, initialState);
  console.log(projectsData);

  let content;
  if (projectsData.selectedProjectId === null) {
    content = <NewProject />;
  } else if (projectsData.selectedProjectId === undefined) {
    content = <DefaultScreen />;
  } else {
    const selectedProject = projectsData.projects.find(project => project.id === projectsData.selectedProjectId);
    const selectedProjectTasks = projectsData.tasks.find(task => task.projectId === projectsData.selectedProjectId);
    content = <ProjectDetails project={selectedProject} projectTasks={selectedProjectTasks.taskList}/>;
  }

  return (
    <ProjectContext.Provider value={projectsData}>
      <ProjectDispatchContext.Provider value={dispatch}>
        <main className="h-screen my-8 flex gap-8">
          {/* <h1 className="my-8 text-center text-5xl font-bold">Project</h1> */}
          <ProjectSidebar />
          {content}
        </main>      
      </ProjectDispatchContext.Provider>
    </ProjectContext.Provider>
  );
}

export default App;
