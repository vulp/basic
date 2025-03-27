<script>
  let todos = [];
  let newTodo = '';
  let errorMessage = '';

  async function fetchTodos() {
    try {
      const response = await fetch('/api/todos');
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      todos = await response.json();
      errorMessage = '';
    } catch (error) {
      console.error('Failed to fetch todos:', error);
      errorMessage = 'Failed to load todos.';
      todos = [];
    }
  }

  async function addTodo() {
    if (newTodo.trim() === '') return;
    try {
      const response = await fetch('/api/todos', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ task: newTodo }),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const addedTodo = await response.json();
      todos = [...todos, addedTodo];
      newTodo = '';
      errorMessage = '';
    } catch (error) {
      console.error('Failed to add todo:', error);
      errorMessage = 'Failed to add todo.';
    }
  }

  function logout() {
    fetch('/logout', { method: 'POST' })
      .then(() => (window.location.href = '/login'))
      .catch((error) => console.error('Logout failed:', error));
  }

  fetchTodos(); // Load todos on component mount
</script>

<main>
  <h1>Todo List</h1>

  <button on:click={logout}>Logout</button>

  {#if errorMessage}
    <p style="color: red;">{errorMessage}</p>
  {/if}

  <input type="text" bind:value={newTodo} placeholder="Add new todo" />
  <button on:click={addTodo}>Add</button>

  <ul>
    {#each todos as todo (todo.id)}
      <li>{todo.task}</li>
    {/each}
  </ul>
</main>

<style>
  main {
    font-family: sans-serif;
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    padding: 8px 0;
    border-bottom: 1px dotted #eee;
  }

  li:last-child {
    border-bottom: none;
  }
</style>