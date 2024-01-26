// ADD_TASK -- to add a new task in the TASK List
// UPDATE_TASK -- update an existing task in the TASK List
// DELETE_TASK -- remove an existing task from the TASK List 

import { createContext, useReducer } from "react";

// COMPLETE_TASK -- mark the task as completed  --> Not implemented
export const Actions = {
    ADD_TASK: 'addTask',
    UPDATE_TASK: 'updateTask',
    DELETE_TASK: 'deleteTask',
    COMPLETE_TASK: 'completeTask',
    ADD_PROJECT: 'addProject',
    ADD_NEW_PROJECT: 'addNewProject',
    DELETE_PROJECT: 'deleteProject',
    SELECT_PROJECT: 'selectProject',
    CANCEL_PROJECT_SELECT: 'cancleProjectSelect'
}

// My Project Management initial state
export const initialState = {
    selectedProjectId: undefined,
    projects: [],
    tasks: []
};

// Two contexts -- 1. to keep the projects data and 2. to hold the dispatch function of the project reducer
export const ProjectContext = createContext(null);
export const ProjectDispatchContext = createContext(null);

export const Reducer = (state, action) => {
    switch(action.type) {
        case 'addTask' : {
            // Whatever variables are declared inside here will stay here
            // don't forgot to return otherwise the control will fall-over to the next case
            console.log("Inside the " + action.type + " reducer function adding task with title: " + action.title);
            console.log(state);
            let currentTasks = state.tasks.filter(task => task.projectId === state.selectedProjectId)[0];
            return {
                ...state,
                // projects: [...state.projects],       No Need not touching the projects here
                tasks: [
                    ...state.tasks.filter(task => task.projectId !== state.selectedProjectId),
                    {
                        ...currentTasks,
                        taskList: [
                            ...currentTasks.taskList,
                            {
                                id: currentTasks.taskList.length + 1,
                                title: action.title,
                                done: false,  // By default a newly added task in to the TASK list will be outstanding
                                addedAt: new Date().toISOString(),
                                updatedAt: new Date().toISOString(),
                            }
                        ]
                    }
                ]
            };
            // return [
            //     ...state,
            //     {
            //         id: state.length + 1,
            //         title: action.title,
            //         done: false,  // By default a newly added task in to the TASK list will be outstanding
            //         addedAt: new Date().toISOString(),
            //         updatedAt: new Date().toISOString(),
            //     }
            // ];
        }
        case 'updateTask' : {
            // .map will return us a new updated array
            console.log("Inside the " + action.type + " reducer.");
            const taskListToBeUpdated = state.tasks.filter(task => task.projectId === state.selectedProjectId)[0];
            return {
                ...state,
                // projects: [...state.projects],       No need
                tasks: state.tasks.map(task => {
                        console.log(task);
                        if(task.projectId !== state.selectedProjectId) {
                            return task;
                        } 
                        else {
                            const updatedTaskList = {
                                ...task,
                                taskList: task.taskList.map(taskForProject => {
                                    if (taskForProject.id === action.taskId) {
                                        return {
                                            ...taskForProject,
                                            done: action.done === undefined ? taskForProject.done : action.done,
                                            updatedAt: action.updatedAt,
                                            title: action.title === undefined ? taskForProject.title : action.title
                                        }
                                    } else {
                                        return taskForProject;
                                    }
                                })
                            }
                            console.log(updatedTaskList);
                            return updatedTaskList;
                        }
                    })
            }
        }
        case 'deleteTask' : {
            // .filter will return us the copy of the old array without those elements
            console.log("Inside the " + action.type + " reducer.");
            let currentTasks = state.tasks.filter(task => task.projectId === state.selectedProjectId)[0];
            return {
                ...state,
                // projects: [...state.projects],   No need
                tasks: [
                    ...state.tasks.filter(task => task.projectId !== state.selectedProjectId),
                    {
                        ...currentTasks,
                        taskList: [
                            ...currentTasks.taskList.filter(task => task.id !== action.taskId)
                        ]
                    }
                ]
            };
            // return state.filter(task => task.id !== action.id);
        }
        case 'addProject' : {
            console.log("Inside the " + action.type + " reducer.");
            const newProject = {
                id: state.projects.length,
                title: action.title,
                description: action.description,
                dueDate: action.dueDate
            }

            return {
                ...state,
                selectedProjectId: undefined,
                projects: [
                    ...state.projects,
                    newProject
                ],
                tasks: [
                    ...state.tasks,
                    {
                        projectId: newProject.id,
                        taskList: []
                    }
                ]
            }
        }
        case 'selectProject': {
            console.log("Inside the " + action.type + " reducer.");
            return {
                ...state,
                // No need as we are not touching the Projects and tasks
                // projects: [...state.projects],
                // tasks: [...state.tasks],
                selectedProjectId: action.projectId
            }   
        }
        case 'deleteProject': {
            console.log("Inside the " + action.type + " reducer.");
            return {
                ...state,
                selectedProjectId: undefined,
                projects: state.projects.filter(project => project.id !== state.selectedProjectId),
                tasks: state.tasks.filter(task => task.projectId !== state.selectedProjectId)
            }
        }
        case 'addNewProject': {
            console.log("Inside the " + action.type + " reducer.");
            return {
                ...state,
                selectedProjectId: null
            }
        }
        case 'cancleProjectSelect': {
            console.log("Inside the " + action.type + " reducer.");
            return {
                ...state,
                selectedProjectId: undefined
            }
        }
        default:
            throw new Error("Unexpected task received.");

    }
};