import NewProject from "./components/NewProject";
import ProjectSidebar from "./components/ProjectSidebar";
import DefaultScreen from "./components/DefaultScreen";
import ProjectDetails from "./components/ProjectDetails";
import { useEffect, useReducer, useState } from "react";
import { Actions, ProjectContext, ProjectDispatchContext, Reducer, initialState } from "./context/ProjectContext";

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
  const [isLoading, setIsLoading] = useState(true);
  console.log(projectsData);

  useEffect(() => {
    fetch("http://localhost:8080/projects/get-all-projects", {
      method: "GET",
    }).then((response) => response.json())
      .then(json => {
        console.log(json);
        dispatch({
          type: Actions.LOAD_DATA,
          data: json
        });
        setIsLoading(false);
      })
      .catch((error) => console.log(error))
  }, []);

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

  const showSpinner = 
  <div class="min-h-[15rem] flex flex-col bg-white rounded-xl h-screen">
  <div class="flex flex-auto flex-col justify-center items-center p-4 md:p-5">
    <div class="flex justify-center">
      <div class="animate-spin inline-block w-6 h-6 border-[3px] border-current border-t-transparent  rounded-full" role="status" aria-label="loading">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
  </div>
</div>;

  return (
    <ProjectContext.Provider value={projectsData}>
      <ProjectDispatchContext.Provider value={dispatch}>
       { isLoading ? showSpinner : <main className="h-screen my-8 flex gap-8">
          {/* <h1 className="my-8 text-center text-5xl font-bold">Project</h1> */}
          <ProjectSidebar/>
          {content}
        </main>}
      </ProjectDispatchContext.Provider>
    </ProjectContext.Provider>
  );
}

export default App;
