package com.loveh.todoapplication.ui.screens.list

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loveh.todoapplication.data.models.Priority
import com.loveh.todoapplication.data.models.ToDoTask
import com.loveh.todoapplication.ui.theme.taskItemBackgroundColor
import com.loveh.todoapplication.ui.theme.taskItemTextColor
import com.loveh.todoapplication.util.RequestState
import com.loveh.todoapplication.util.SearchAppBarState

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    if (sortState is RequestState.Success) {
        Log.d("ListContent", "sortState was: ${sortState.toString()}")
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchedTasks.data,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(tasks = allTasks.data, navigateToTaskScreen = navigateToTaskScreen)
                }
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(tasks = lowPriorityTasks, navigateToTaskScreen = navigateToTaskScreen)
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(tasks = highPriorityTasks, navigateToTaskScreen = navigateToTaskScreen)
            }
        }

    } else {
        Log.d("ListContent", "sortState was: ${sortState.toString()}")
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(toDoTasks = tasks, navigateToTaskScreen = navigateToTaskScreen)
    }
}
@Composable
fun DisplayTasks(
    toDoTasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    // LazyColumn is like recyclerView from XML.
    // Dynamically sized list, represented graphically.
    LazyColumn {
        items(
            items = toDoTasks,
            key = { task ->
                task.id
            }
        ) {task ->
            TaskItem(toDoTask = task, navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = 2.dp,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }

    )
    {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        )
        {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(id = 0,
            title = "title",
            description = "desc",
            priority = Priority.HIGH,
            ),
        navigateToTaskScreen = {}
    )
}