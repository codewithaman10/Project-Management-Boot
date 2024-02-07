import NewProject from "./NewProject";
import ProjectSidebar from "./ProjectSidebar";
import DefaultScreen from "./DefaultScreen";
import ProjectDetails from "./ProjectDetails";
import { useEffect, useReducer, useState } from "react";
import { Actions, ProjectContext, ProjectDispatchContext, Reducer, initialState } from "../context/ProjectContext";
import Header from "./common/Header";
import Spinner from "./common/Spinner";
import Login from "./Login";
import { useUser } from "../context/UserProvider";

export default function ProjectManager() {
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
  const [isLoading, setIsLoading] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const user = useUser();
  
  // Props for our login form
  const [username, setUserName] = useState(undefined);
  const [password, setPassword] = useState(undefined);
  console.log(projectsData);

  useEffect(() => {
    fetch("/projects/get-all-projects", {
      method: "GET",
      header: {
          'Authorization': `Bearer ${user.token}`
      }
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
    const selectedProject = projectsData.selectedProjectDetails;
    content = <ProjectDetails selectedProject={selectedProject} />;
  }

  function handleLogin(e) {
    e.preventDefault();
    // Call backend to validate the user details and generate the jwt token
    console.log(username + " " + password);
  }

  return (
    <>
      <Header />
      <ProjectContext.Provider value={projectsData}>
        <ProjectDispatchContext.Provider value={dispatch}>
        { isLoading ? <Spinner /> : <main className="h-screen flex gap-8">
          {/* <h1 className="my-8 text-center text-5xl font-bold">Project</h1> */}
          <ProjectSidebar/>
          {content}
        </main>}

        </ProjectDispatchContext.Provider>
      </ProjectContext.Provider>
    </>
  );
}