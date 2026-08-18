const form = document.getElementById('todoForm');
const input = document.getElementById('todoInput');
const list = document.getElementById('todoList');
const count = document.getElementById('taskCount');
const emptyState = document.getElementById('emptyState');
const clearCompleted = document.getElementById('clearCompleted');

let todos = JSON.parse(localStorage.getItem('todos') || '[]');

function save() {
  localStorage.setItem('todos', JSON.stringify(todos));
}

function render() {
  list.innerHTML = '';
  todos.forEach((todo) => {
    const li = document.createElement('li');
    li.className = `todo-item${todo.completed ? ' completed' : ''}`;

    const checkbox = document.createElement('input');
    checkbox.type = 'checkbox';
    checkbox.checked = todo.completed;
    checkbox.addEventListener('change', () => {
      todo.completed = checkbox.checked;
      save();
      render();
    });

    const text = document.createElement('span');
    text.textContent = todo.text;

    const remove = document.createElement('button');
    remove.type = 'button';
    remove.className = 'delete';
    remove.textContent = 'Delete';
    remove.addEventListener('click', () => {
      todos = todos.filter((item) => item.id !== todo.id);
      save();
      render();
    });

    li.append(checkbox, text, remove);
    list.appendChild(li);
  });

  const remaining = todos.filter((todo) => !todo.completed).length;
  count.textContent = `${remaining} ${remaining === 1 ? 'task' : 'tasks'} remaining`;
  emptyState.hidden = todos.length !== 0;
}

form.addEventListener('submit', (event) => {
  event.preventDefault();
  const text = input.value.trim();
  if (!text) return;

  todos.push({ id: Date.now(), text, completed: false });
  input.value = '';
  save();
  render();
  input.focus();
});

clearCompleted.addEventListener('click', () => {
  todos = todos.filter((todo) => !todo.completed);
  save();
  render();
});

render();
