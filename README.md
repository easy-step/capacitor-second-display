# capacitor-second-display

Capacitor-second-display is a custom plugin for Android to show web content on second displays of devices

## Install

```bash
npm install capacitor-second-display
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`showOnSecondScreen(...)`](#showonsecondscreen)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### showOnSecondScreen(...)

```typescript
showOnSecondScreen(options: { url: string; }) => Promise<void>
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ url: string; }</code> |

--------------------

</docgen-api>
